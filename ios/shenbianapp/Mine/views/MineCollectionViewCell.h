//
//  MineCollectionViewCell.h
//  shenbianapp
//
//  Created by   on 17/7/16.
//  Copyright © 2017  . All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MineCollectionViewCell : UICollectionViewCell
@property (weak, nonatomic) IBOutlet UILabel *titleName;
@property (weak, nonatomic) IBOutlet UIImageView *iconImageView;
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;

@end
