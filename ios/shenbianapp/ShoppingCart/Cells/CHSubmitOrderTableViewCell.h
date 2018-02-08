//
//  CHSubmitOrderTableViewCell.h
//  shenbianapp
//
//  Created by book on 2017/12/29.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^getNote)(NSString *note);

@interface CHSubmitOrderTableViewCell : UITableViewCell
@property(nonatomic,strong)NSArray *dataArray;
@property(nonatomic,strong)NSIndexPath *indexPath;
@property(nonatomic,strong)getNote getNote;
@property(nonatomic,copy)NSString *tailText;
@end
