//
//  HeaderView.h
//  Extand TableView
//
//  Created by shenliping on 16/4/14.
//  Copyright © 2016 shenliping. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^openBlock)(NSInteger section);
typedef void(^closeBlock)(NSInteger section);
typedef void(^clickServiceTypeBlock)(NSUInteger type);
typedef void(^clickServicePriceBlock)(NSString* price);
typedef void(^selectedServiceKindBlock)(NSString* kind);

@interface HeaderView : UITableViewHeaderFooterView

@property (assign, nonatomic) BOOL isOpen;
@property (assign, nonatomic) NSInteger section;
@property (strong, nonatomic) UILabel *nameLabel;
@property (strong, nonatomic) UILabel *tailLabel;
@property (strong, nonatomic) UIImageView *imageView;
@property (copy, nonatomic) openBlock openblock;
@property (copy, nonatomic) closeBlock closeblock;
@property (copy, nonatomic) clickServiceTypeBlock serviceTypeblock;
@property (copy, nonatomic) clickServicePriceBlock servicePriceblock;

- (instancetype)initWithFrame:(CGRect)frame IsOpen:(BOOL)isOpen;

@end
